package org.green.backend.security;

import lombok.RequiredArgsConstructor;
import org.green.backend.dto.common.SecurityUserDto;
import org.green.backend.repository.dao.common.UserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SecurityUserDto user = userDao.findByIdSecurity(username);

        if (user == null) {
            throw new UsernameNotFoundException("아이디 및 비밀번호가 일치하지 않거나 비활성화된 계정입니다.");
        }

        return new CustomUserDetails(user);
    }

}

