package com.hydrogenhr.model.projections;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 11:20 PM
 */
public interface PasswordResetTokenProjection {
    public String getId();

    public String getToken();

    public String getExpiryDate();

    public String getUserId();

    public String getUserUsername();

    public String getUserEmail();
}
