package com.jwzp_kr_kj;

public class Logs {

    static public String logStart(){
        return "Start application";
    }

    static public String logGetAll(Class type){
        return "All: " + type;
    }

    static public String logNotAccepted(Class type){
        return "Something is wrong: " + type;
    }

    static public String logGetById(Class type, int id){
        return "Find by id : " + type + " with id: " + id;
    }
    static public String logAdded(Object saved, int id) {
        return "Added: " + saved.getClass() + " id: " + id;
    }

    static public String logDeleted(Object deleted, int id) {
        return "Deleted: " + deleted.getClass() + " id: " + id;
    }

    static public String logUpdated(Object updated, int id) {
        return "Updated: " + updated.getClass() + " id: " + id;
    }

    static public String logNotFound(Class type, int id) {
        return "Object: " + type + " with id: " + id + " not found";
    }

    static public String logException(Exception e) {
        return "Exception occurred: " + e.getClass() + " message: " + e.getMessage();
    }
}
