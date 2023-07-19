package com.roadmap.myapp.service.dto


class BlogSearchDTO {
    private Long id;


    private String name;


    private String handle;

    private String user;


    BlogSearchDTO(Long id, String name, String handle, String user) {
        this.id = id
        this.name = name
        this.handle = handle
        this.user = user
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getHandle() {
        return handle
    }

    void setHandle(String handle) {
        this.handle = handle
    }

    String getUser() {
        return user
    }

    void setUser(String user) {
        this.user = user
    }
}
