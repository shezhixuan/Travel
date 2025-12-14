package org.zjsru.domain;

public class TicketRequest {
    private Long spotId;
    private Long userId;
    private String userName;

    public Long getSpotId() {
        return spotId;
    }

    public void setSpotId(Long spotId) {
        this.spotId = spotId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getPointsToUse() {
        return pointsToUse;
    }

    public void setPointsToUse(int pointsToUse) {
        this.pointsToUse = pointsToUse;
    }

    private String userPhone;
    private int pointsToUse;
}
