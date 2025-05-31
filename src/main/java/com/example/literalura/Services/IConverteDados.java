package com.example.literalura.Services;

public interface IConverteDados {
    <T> T ObterDados(String json, Class<T> classe);
}
