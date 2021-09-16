package com.api.kruger.response;


public class ModelExample {

    private String name = "Servicio rest";
    private String status = "200";
    private String description = "Servicio para consultar api";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
