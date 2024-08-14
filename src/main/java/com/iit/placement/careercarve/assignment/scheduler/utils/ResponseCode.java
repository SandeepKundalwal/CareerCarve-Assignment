package com.iit.placement.careercarve.assignment.scheduler.utils;

public enum ResponseCode {
    SUCCESS(0, "Request succeeded"),
    //OK(200, "Accepted"),
    FAILED(1, "Request failed"),
    UNKNOWN(2, "Unknown"),
    SERVER_INTERNAL_SERVER_ERROR(501, "Internal_Server_error"),
    CLIENT_USER_ID_NOT_EXISTING(1012, "User_is_not_registered_please_register_the_user"),
    CLIENT_INVALID_REQ_PARAM_USER_NAME(1013,"Invalid_user_name"),
    CLIENT_INVALID_REQ_PARAM_USER_EMAIL(2000, "Invalid Email_Id"),
    CLIENT_INVALID_REQ_PARAM_MOBILE_NUM(1014,"Invalid_Mobile_Number"),
    CLIENT_USER_MOBILE_EXISTING(1015, "Mobile_Number_already_In_Use_please_register_with_different_Mobile_number"),
    CLIENT_INVALID_REQ_PARAM_USER_TYPE(1016,"Invalid_User_Type"),
    CLIENT_INVALID_REQ_PARAM_USERID_PASSWORD(1017,"Invalid_username_or_password"),
    CLIENT_INVALID_REQ_PARAM_USER_ID(1018,"Invalid_user_id"),
    CLIENT_INVALID_REQ_PARAM_GENDER(1019,"Invalid_gender"),
    CLIENT_INVALID_REQ_PARAM_RELATION(1020,"Invalid_Relation"),
    CLIENT_INVALID_REQ_PARAM_LITERACY(1021,"Invalid_education"),
    CLIENT_INVALID_REQ_PARAM_HEAD_NAME(1022,"Invalid_head_name"),
    CLIENT_INVALID_REQ_PARAM_ADDRESS(1023,"Invalid_Address/Please provide valid address"),
    CLIENT_INVALID_REQ_PARAM_COORDINATES(1024,"Invalid_Coordinates/Please provide coordinates"),
    CLIENT_HAS_FARMER_WITH_USER_ID(1025,"Farmer_already_exists_with_this_user_id"),
    CLIENT_INVALID_REQ_PARAM_FARMER_ID(1026,"Invalid farmer_id"),
    CLIENT_INVALID_REQ_PARAM_FARMER_USER_ID(1027,"Invalid farmer_id or user_id"),
    CLIENT_NO_FARMER_CONFIGURED(1028,"No farmer(s) found"),
    CLIENT_INVALID_REQ_PARAM_DATE(1029,"Invalid_date"),
    CLIENT_INVALID_REQ_PARAM_FARM_ID(1030,"Invalid farm Id"),
    CLIENT_INVALID_REQ_PARAM_FARM_NAME(1031,"Invalid farm Name"),
    CLIENT_INVALID_REQ_PARAM_FARM_TYPE(1032,"Invalid farm Type"),
    CLIENT_INVALID_REQ_PARAM_FARM_CATEGORY(1033,"Invalid farm size category"),
    CLIENT_INVALID_REQ_PARAM_FARM_SIZE(1034,"Invalid size of farm"),
    CLIENT_HAS_FARM_WITH_FARMER_ID(1035,"Farm_name_already_exists,_please_try_again_with_a_different_Farm_name"),
    CLIENT_REQ_FARM_WITH_USER_ID_IS_CONFLICT(1036,"Farm_name_repeated, please_provide_different_Farm_name_for_each_farm"),
    CLIENT_HAS_NO_FARM_WITH_USER_ID(1037,"Farm_Not_Present_With_This_Name"),
    CLIENT_INVALID_REQ_PARAM_FARM_PARCEL_NAME(1038,"Invalid parcel name"),
    CLIENT_PARCEL_NAME_EXISTING(1039,"Parcel_name_already_exists,_please_try_again_with_a_different_Parcel_name"),
    CLIENT_INVALID_REQ_PARAM_PARCEL_NAME(1040,"Invalid farm Name"),
    CLIENT_HAS_NO_PARCEL_WITH_USER_ID(1041,"Parcel_Not_Present_With_This_Name"),
    CLIENT_MISSING_ID(1042,"Missing_ID"),
    CLIENT_NO_FARM_CONFIGURED(1043,"No_Farms_Configured"),
    CLIENT_INVALID_REQ_PARAM_CROP(1044,"Invalid_Crop"),

    CLIENT_INVALID_REQ_PARAM_PARCEL_ID(1045,"Invalid_Parcel_ID"),
    CLIENT_NO_PARCEL_CONFIGURED(1046,"No_Parcel_Configured"),
    CLIENT_INVALID_REQ_PARAM_START_DATE(1047,"Invalid_start_date"),
    CLIENT_INVALID_REQ_PARAM_END_DATE(1048,"Invalid_end_date"),
    CLIENT_INVALID_REQ_PARAM_START_DATE_LATER_END_DATE(1049,"Start_date_later_than_end_date"),
    CLIENT_NO_USER_CONFIGURED(1050,"No user(s) found"),
    CLIENT_INVALID_REQ_PARAM_PASSWORD(1051, "Password can't be empty "),

    UNAUTHORIZED_REQUEST(401, "Unauthorized request"),
    MISSING_OR_INVALID_AUTHORIZATION_HEADER(401, "Missing or Invalid Authorization Header.");

    int code;
    String text;

    ResponseCode(int code, String text){
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }


    public String getText() {
        return text;
    }

    public static String getTextByCode(int code) {
        for (ResponseCode responseCode : ResponseCode.values()) {
            if (responseCode.getCode() == code) {
                return responseCode.getText();
            }
        }
        return ResponseCode.UNKNOWN.getText();
    }
    public String toString() {
        return code + " - " + text;
    }


}

