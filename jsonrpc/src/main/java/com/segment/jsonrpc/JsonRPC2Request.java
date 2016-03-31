package com.segment.jsonrpc;

import java.util.Collections;
import java.util.List;
import java.util.Random;

class JsonRPC2Request {
    static final Random RANDOM = new Random();

    final String jsonrpc;
    final String method;
    final List<Object> params;
    final long id;

    JsonRPC2Request(String method, List<Object> params, long id, String jsonrpc) {
        this.method = method;
        this.params = params;
        this.id = id;
        this.jsonrpc = jsonrpc;
    }

    static JsonRPC2Request create(String method, Object args) {
        long id = Math.abs(RANDOM.nextInt());
        return new JsonRPC2Request(method, Collections.singletonList(args), id, "2.0");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsonRPC2Request that = (JsonRPC2Request) o;

        if (id != that.id) return false;
        //noinspection SimplifiableIfStatement
        if (!method.equals(that.method)) return false;
        return params.equals(that.params);
    }

    @Override
    public int hashCode() {
        int result = method.hashCode();
        result = 31 * result + params.hashCode();
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }
}
