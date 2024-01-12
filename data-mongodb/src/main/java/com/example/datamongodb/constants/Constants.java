package com.example.datamongodb.constants;

public class Constants {

    public static final String DATA_ERROR = "{'message':'unable to save request!'}";
    public static final String GET_DATA_ERROR = "Something went wrong, please, try again";
    public static final String BAD_PUT_REQUEST = "Something went wrong, data not updated";

    public static final String DELETED_OK = """
            
            {
                "acknowledged": true,
                "deletedCount": 1
            }
            """;
}