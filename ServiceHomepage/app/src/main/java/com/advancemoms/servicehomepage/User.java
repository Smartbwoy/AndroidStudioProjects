package com.advancemoms.servicehomepage;
/**
 * Created by alway on 3/3/2018.
 */

public class User {

        public String username;
        public String email;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String username, String email) {
            this.username = username;
            this.email = email;
        }

    }
