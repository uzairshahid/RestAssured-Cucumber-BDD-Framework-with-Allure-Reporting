package Genderize;

public interface GenderizeAPIResources {
    String CREATE_PRODUCT = "/medicines/createProduct";
    String UPDATE_PRODUCT = "/medicines/updateProduct";
    String USER_LOGIN = "/medicines/login";

    String GET_USER_BY_NAME = "?name=%s";
    String GET_USER_BY_NAME_AND_COUNTRY = "?name=%s&country_id=%s";


}
