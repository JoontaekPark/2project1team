package org.green.backend.security;

import org.green.backend.dto.common.SecurityUserDto;
import org.green.backend.dto.common.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private SecurityUserDto user;

    public CustomUserDetails(SecurityUserDto user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getUserGbnCd();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPw();
    }

    @Override
    public String getUsername() {
        return user.getId();
    }

    public String getName(){
        return user.getName();
    }

    public String getUseYn(){
        return user.getUseYn();
    }

    public String getUserGbnCd(){
        return user.getUserGbnCd();
    }

}
