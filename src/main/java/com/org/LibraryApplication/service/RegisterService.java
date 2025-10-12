package com.org.LibraryApplication.service;

import com.org.LibraryApplication.dto.RegisterDto;
import java.util.List;

public interface RegisterService {
    List<String> registerUsers(RegisterDto regDto);
}
