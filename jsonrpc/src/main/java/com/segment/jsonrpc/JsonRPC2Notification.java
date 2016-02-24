package com.segment.jsonrpc;

import java.util.Collections;
import java.util.List;

class JsonRPC2Notification {

    final String jsonrpc;
    final String method;
    final List<Object> params;

    JsonRPC2Notification(String method, List<Object> params, String jsonrpc) {
        this.method = method;
        this.params = params;
        this.jsonrpc = jsonrpc;
    }

    static JsonRPC2Notification create(String method, Object args) {
        return new JsonRPC2Notification(method, Collections.singletonList(args), "2.0");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsonRPC2Notification that = (JsonRPC2Notification) o;

        //noinspection SimplifiableIfStatement
        if (!method.equals(that.method)) return false;
        return params.equals(that.params);
    }

    @Override
    public int hashCode() {
        int result = method.hashCode();
        result = 31 * result + params.hashCode();
        return result;
    }
}
