package com.hydrogenhr.core.installer;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/30/24
 * Time: 3:38 PM
 */
public interface SystemAuthority {
    public interface UserAuthority {
        @Getter
        @AllArgsConstructor
        public enum OrganizationAuthority {
            CREATE_ORGANIZATION("CREATE_ORGANIZATION"),
            UPDATE_ORGANIZATION("UPDATE_ORGANIZATION"),
            VIEW_ORGANIZATION("VIEW_ORGANIZATION"),
            VIEW_ORGANIZATIONS("VIEW_ORGANIZATIONS");
            private String authority;
        }

        @Getter
        @AllArgsConstructor
        public enum PrivilegeAuthority {
            CREATE_PRIVILEGE("CREATE_PRIVILEGE"),
            UPDATE_PRIVILEGE("UPDATE_PRIVILEGE"),
            VIEW_PRIVILEGE("VIEW_PRIVILEGE"),
            VIEW_PRIVILEGES("VIEW_PRIVILEGES");
            private String authority;
        }

        @Getter
        @AllArgsConstructor
        public enum RoleAuthority {
            CREATE_ROLE("CREATE_ROLE"),
            UPDATE_ROLE("UPDATE_ROLE"),
            VIEW_ROLE("VIEW_ROLE"),
            VIEW_ROLES("VIEW_ROLES");
            private String authority;
        }


        @Getter
        @AllArgsConstructor
        public enum OauthUserAuthority {
            CREATE_USER("CREATE_USER"),
            UPDATE_USER("UPDATE_USER"),
            CHANGE_PASSWORD("CHANGE_PASSWORD"),
            RESET_USER_PASSWORD("RESET_USER_PASSWORD"),
            VIEW_USER("VIEW_USER"),
            VIEW_USERS("VIEW_USERS");
            private String authority;
        }
    }

    public interface VdmsSystemAuthority {
        @Getter
        @AllArgsConstructor
        public enum GenericAuthorities {
            SUPER_ADMIN("SUPER_ADMIN"),
            SYSTEM_ADMIN("SYSTEM_ADMIN"),
            APPLICATION_ADMIN("APPLICATION_ADMIN"),
            ORGANIZATION_ADMIN("ORGANIZATION_ADMIN"),
            LOCATION_MANAGER("LOCATION_MANAGER"),
            USER("USER"),
            OIDC_USER("OIDC_USER");
            private String authority;
        }

        @Getter
        @AllArgsConstructor
        public enum UserAuthority {

            CREATE_VDMS_USER("CREATE_VDMS_USER"),
            UPDATE_VDMS_USER("UPDATE_VDMS_USER"),
            VIEW_VDMS_USER("VIEW_VDMS_USER"),
            VIEW_VDMS_USERS("VIEW_VDMS_USERS");

            private String authority;
        }

        @Getter
        @AllArgsConstructor
        public enum DeliveryAuthority {

            CREATE_DELIVERY("CREATE_DELIVERY"),
            UPDATE_DELIVERY("UPDATE_DELIVERY"),
            REMINDER_DELIVERY("REMINDER_DELIVERY"),
            SIGN_IN_OUT_DELIVERY("SIGN_IN_OUT_DELIVERY"),
            VIEW_DELIVERY("VIEW_DELIVERY"),
            VIEW_DELIVERIES("VIEW_DELIVERIES");

            private String authority;
        }

        @Getter
        @AllArgsConstructor
        public enum BrandAuthority {
            CREATE_BRAND("CREATE_BRAND"),
            UPDATE_BRAND("UPDATE_BRAND"),
            VIEW_BRAND("VIEW_BRAND"),
            VIEW_BRANDS("VIEW_BRANDS");

            private String authority;
        }

        @Getter
        @AllArgsConstructor
        public enum DepartmentAuthority {
            CREATE_DEPARTMENT("CREATE_DEPARTMENT"),
            UPDATE_DEPARTMENT("UPDATE_DEPARTMENT"),
            VIEW_DEPARTMENT("VIEW_DEPARTMENT"),
            VIEW_DEPARTMENTS("VIEW_DEPARTMENTS");

            private String authority;
        }

        @Getter
        @AllArgsConstructor
        public enum EmployeeSettingAuthority {
            CREATE_EMPLOYEE_SETTING("CREATE_EMPLOYEE_SETTING"),
            UPDATE_EMPLOYEE_SETTING("UPDATE_EMPLOYEE_SETTING"),
            VIEW_EMPLOYEE_SETTING("VIEW_EMPLOYEE_SETTING"),
            VIEW_EMPLOYEE_SETTINGS("VIEW_EMPLOYEE_SETTINGS");

            private String authority;
        }

        @Getter
        @AllArgsConstructor
        public enum LocationAuthority {

            CREATE_LOCATION("CREATE_LOCATION"),
            UPDATE_LOCATION("UPDATE_LOCATION"),
            VIEW_LOCATION("VIEW_LOCATION"),
            VIEW_LOCATIONS("VIEW_LOCATIONS");

            private String authority;
        }

        @Getter
        @AllArgsConstructor
        public enum VisitorAuthority {

            CREATE_VISITOR("CREATE_VISITOR"),
            UPDATE_VISITOR("UPDATE_VISITOR"),
            SIGN_IN_OUT_VISITOR("SIGN_IN_OUT_VISITOR"),
            VIEW_VISITOR("VIEW_VISITOR"),
            VIEW_VISITORS("VIEW_VISITORS");

            private String authority;
        }

        @Getter
        @AllArgsConstructor
        public enum VisitorSettingAuthority {

            CREATE_VISITOR_SETTING("CREATE_VISITOR_SETTING"),
            UPDATE_VISITOR_SETTING("UPDATE_VISITOR_SETTING"),
            VIEW_VISITOR_SETTING("VIEW_VISITOR_SETTING"),
            VIEW_VISITOR_SETTINGS("VIEW_VISITOR_SETTINGS");

            private String authority;
        }

        @Getter
        @AllArgsConstructor
        public enum DeviceAuthority {

            CREATE_DEVICE("CREATE_DEVICE"),
            UPDATE_DEVICE("UPDATE_DEVICE"),
            VIEW_DEVICE("VIEW_DEVICE"),
            VIEW_DEVICES("VIEW_DEVICES");

            private String authority;
        }
    }

    public interface NotificationSystemAuthority {
        @Getter
        @AllArgsConstructor
        public enum EmailSetupAuthorities {
            CREATE_EMAIL_SETUP("CREATE_EMAIL_SETUP"),
            UPDATE_EMAIL_SETUP("UPDATE_EMAIL_SETUP"),
            TEST_EMAIL_SETUP("TEST_EMAIL_SETUP"),
            VIEW_EMAIL_SETUP("VIEW_EMAIL_SETUP"),
            VIEW_EMAIL_SETUPS("VIEW_EMAIL_SETUPS");
            private String authority;
        }
    }
}
