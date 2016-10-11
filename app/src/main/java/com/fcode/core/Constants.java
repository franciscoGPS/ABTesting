

package com.fcode.core;

/**
 * Bootstrap constants
 */
public final class Constants {
    private Constants() {}

    public static final class Auth {
        private Auth() {}

        /**
         * Account type id
         */
        public static final String BOOTSTRAP_ACCOUNT_TYPE = "com.fcode";

        /**
         * Account name
         */
        public static final String BOOTSTRAP_ACCOUNT_NAME = "Flash";

        /**
         * Provider id
         */
        public static final String BOOTSTRAP_PROVIDER_AUTHORITY = "com.fcode.sync";

        /**
         * Auth token type
         */
        public static final String AUTHTOKEN_TYPE = Http.AUTH_HEADER_TOKEN_TYPE;

        /**
         * User id
         */
        public static final String USER = "user";

        /**
         * User email
         */
        public static final String USER_EMAIL = "user_email";
        /**
         * User email
         */
        public static final String USER_ID = "user_id";
        /**
         * User email
         */
        public static final String USER_ROLE = "user_role";
        /**
         * User email
         */
        public static final String USER_NAME = "user_name";
    }

    /**
     * All HTTP is done through a REST style API built for demonstration purposes on Parse.com
     * Thanks to the nice people at Parse for creating such a nice system for us to use for bootstrap!
     */
    public static final class Http {
        private Http() {}



        /**
         * Base URL for all requests
         */
        //public static final String URL_BASE = "https://flash-staging.herokuapp.com";
        public static final String URL_BASE = "http://192.168.15.9:3001";

        /*
        * Api URL
        * */
        public static final String API_URL = "/api/v1";

        /**
         * URL Service Orders
         * */
        public static final String URL_SERVICE_ORDERS = "/service_orders";

        /**
         * URL Service Orders
         * */
        public static final String URL_CUSTOMERS = "/customers";


        /**
         * Authentication URL
         */
        public static final String URL_AUTH_FRAG = "/auth/sign_in";
        public static final String URL_AUTH = API_URL+ URL_AUTH_FRAG;



        public static final String GET_REQUEST_METHOD = "GET";

        public static final String POST_REQUEST_METHOD = "POST";
        public static final String AUTH_HEADER_CLIENT = "client";
        public static final String AUTH_HEADER_ACCESS_TOKEN = "access-token";
        public static final String AUTH_HEADER_TOKEN_TYPE = "token-type";
        public static final String AUTH_HEADER_EXPIRY = "expiry";
        public static final String AUTH_HEADER_UID = "uid";
        public static final String FLASH_SHARED_PREFS = "Flash-u";


        /**
         * List Users URL
         */
        public static final String URL_USERS_FRAG =  "/users";
        public static final String URL_USERS = API_URL + URL_USERS_FRAG;


        /**
         * List News URL
         */
        public static final String URL_NEWS_FRAG = "/service_orders";
        public static final String URL_NEWS = API_URL + URL_NEWS_FRAG;


        /**
         * List Checkin's URL
         */
        public static final String URL_CHECKINS_FRAG = "/1/classes/Locations";
        public static final String URL_CHECKINS = URL_BASE + URL_CHECKINS_FRAG;

        /**
         * PARAMS for auth
         */
        public static final String PARAM_USERNAME = "email";
        public static final String PARAM_PASSWORD = "password";

        /*
        *   List of car makes and models
        */
        public static final String URL_GET_CAR_MAKES = "/get_car_makes";
        public static final String URL_GET_CAR_MODELS = "/get_car_models";
        public static final String QUERY_PARAM = "q";
        public static final String MAKES_SET = "makes_set";




    }


    public static final class Extra {
        private Extra() {}

        public static final String NEWS_ITEM = "news_item";

        public static final String USER = "user";

        public static final String SERVICE_ORDER = "service_order";

        public static final String CUSTOMER = "customer";

        public static final String SERVICE_ORDER_WRAPPER = "service_order_wrapper";


    }

    public static final class Intent {
        private Intent() {}

        /**
         * Action prefix for all intents created
         */
        public static final String INTENT_PREFIX = "com.fcode.";

    }

    public static class Notification {
        private Notification() {
        }

        public static final int TIMER_NOTIFICATION_ID = 1000; // Why 1000? Why not? :)
    }

}


