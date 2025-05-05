package com.tk.airbnb.Airbnb.Util;

import com.tk.airbnb.Airbnb.Model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class AppUtils {

    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
