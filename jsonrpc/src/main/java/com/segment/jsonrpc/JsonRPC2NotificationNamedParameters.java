package com.segment.jsonrpc;

class JsonRPC2NotificationNamedParameters {

    final String jsonrpc;
    final String method;
    final Object params;

    JsonRPC2NotificationNamedParameters(String method, Object params, String jsonrpc) {
        this.method = method;
        this.params = params;
        this.jsonrpc = jsonrpc;
    }

    static JsonRPC2NotificationNamedParameters create(String method, Object args) {
        return new JsonRPC2NotificationNamedParameters(method, args, "2.0");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsonRPC2NotificationNamedParameters that = (JsonRPC2NotificationNamedParameters) o;

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
