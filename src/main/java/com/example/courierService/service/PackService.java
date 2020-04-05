package com.example.courierService.service;

import com.example.courierService.model.Pack;
import com.example.courierService.model.UserCourier;
import com.example.courierService.model.UserForwarder;
import com.example.courierService.repository.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PackService {

    @Autowired
    private PackRepository packRepository;

    @Autowired
    private UserCourierService userCourierService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private AskApiService askApiService;

    public Pack createNewPack(HttpServletRequest httpServletRequest, String recipient, String sender, double weight) {
        LocalDate postingDate = askApiService.askApiAboutDate().plusDays(1);
        String trackingKey = generateTrackingKey();
        UserForwarder userForwarder = sessionService.checkUserForwarderSession(httpServletRequest);

        if (postingDate != null && userForwarder != null) {
            Pack pack = new Pack(null, recipient, sender, weight, postingDate, null, trackingKey, null, userForwarder);
            packRepository.save(pack);
            return pack;
        } else {
            return null;
        }
    }

    private String generateTrackingKey() {
        return UUID.randomUUID().toString();
    }

    public List<Pack> forwarderPackList(HttpServletRequest httpServletRequest) {
        UserForwarder userForwarder = sessionService.checkUserForwarderSession(httpServletRequest);
        List<Pack> packList = new ArrayList<>();

        if (userForwarder != null) packList = packRepository.findAllByUserForwarderAndUserCourierIsNull(userForwarder);
        return packList;
    }

    public List<Pack> courierPackList(HttpServletRequest request) {
        UserCourier userCourier = sessionService.checkUserCourierSession(request);
        List<Pack> packList = new ArrayList<>();

        if (userCourier != null) packList = packRepository.findAllByUserCourierAndDeliveryDateIsNull(userCourier);
        return packList;
    }

    public boolean addPackToCourier(int courierId, int packId) {
        UserCourier userCourier = userCourierService.findById(courierId);
        Pack pack = findById(packId);
        if (userCourier != null && pack != null) {
            pack.setUserCourier(userCourier);
            pack.setPostingDate(pack.getPostingDate().plusDays(1));
            packRepository.save(pack);
            return true;
        }
        return false;
    }

    private Pack findById(int packId) {
        return packRepository.findById(packId).isPresent() ? packRepository.findById(packId).get() : null;
    }

    public boolean setDeliveryDateToPack(int packId) {
        Pack pack = findById(packId);
        LocalDate localDateNow = LocalDate.now();
        if (pack != null) {
            pack.setDeliveryDate(localDateNow);
            packRepository.save(pack);
            return true;
        }
        return false;
    }

    public void deletePack(int packId) {
        Pack pack = findById(packId);
        if (pack != null)
            packRepository.delete(pack);
    }

    public void changeCourier(int newCourierId, int packId) {
        UserCourier courier = userCourierService.findById(newCourierId);
        Pack pack = findById(packId);

        if (pack != null) {
            if (newCourierId == -1) {
                pack.setUserCourier(null);
                pack.setPostingDate(pack.getPostingDate().plusDays(1));
                packRepository.save(pack);
            } else if (courier != null) {
                pack.setUserCourier(courier);
                pack.setPostingDate(pack.getPostingDate().plusDays(1));
                packRepository.save(pack);
            }
        }
    }
}
