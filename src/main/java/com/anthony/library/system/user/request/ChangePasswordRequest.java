package com.anthony.library.system.user.request;

public record ChangePasswordRequest(
        String currentPassword,
        String newPassword,
        String confirmationPassword) {
}
