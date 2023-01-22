package cz.mendelu.ja.leteckaposta.utils;

import lombok.Getter;

@Getter
public class Response<T> {

    public static <R> Response<R> of(R content) {
        var response = new Response<R>();
        response.content = content;
        return response;
    }
    private Response() {}

    private T content;

}
