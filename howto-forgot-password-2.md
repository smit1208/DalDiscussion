# How-to: Forgot Password

This document is how to use forgot password feature.

## Run application

Run java class: com.macs.group6.daldiscussion.DaldiscussionApplication

Except "/login", "/logout", "/forgot-password", "/reset-password", all pages require logged in to view.

![Login](https://imgur.com/download/tYuvJqS)

Test username/password is geetopod/geetopod

## Create user for testing

In com.macs.group6.daldiscussion.ForgotPasswordTests, add following lines:
```
    @Test
    public void createUser_Success() {
        String username = "dald";
        String password = "dald";
        String email = "support@dald.com";
        String firstName = "DAL";
        String lastName = "User";
        String middleName = "";
        try {
            List<User> userList = UserDAO.instance().findByUsername(username);
            if (userList.size() == 0) {
                User user = new User();
                user.code = UUID.randomUUID().toString().replaceAll("-", "");
                user.username = username;
                user.password = password;
                user.email = email;
                user.firstName = firstName;
                user.lastName = lastName;
                user.middleName = middleName;
                UserDAO.instance().save(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```

Enter your information for following lines:
```
        String username = "dald";
        String password = "dald";
        String email = "support@dald.com";
        String firstName = "DAL";
        String lastName = "User";
        String middleName = "";
```

Run that test.

## Start forgot password

Open http://localhost:9090/forgot-password

![Forgot Password](https://imgur.com/download/JepoLQ4)

Enter your email which is associated to created user, click "Reset Password"

Open your mailbox:

![Mailbox](https://imgur.com/download/3t2uXLW)

Click on "this link"

![Reset Password](https://imgur.com/download/5MazvV2)

Enter "Password" & "Retype Password", click on "Change Password"