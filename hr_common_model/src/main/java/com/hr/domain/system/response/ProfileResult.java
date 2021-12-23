package com.hr.domain.system.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ProfileResult implements Serializable {
    private String userId;
    private String mobile;
    private String username;
    private String company;
    private String companyId;
    private Map<String, Object> roles = new HashMap<>();


}
