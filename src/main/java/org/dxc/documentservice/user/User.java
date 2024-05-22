package org.dxc.documentservice.user;

import org.dxc.documentservice.user.enums.ChatStatus;
import org.dxc.documentservice.user.enums.FamilyStatus;
import org.dxc.documentservice.user.enums.WorkStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private FamilyStatus familyStatus;
    private WorkStatus workStatus;
    private ChatStatus chatStatus;
    private String mobile;
    private String email;
    private String password;
    private String description;
    private LocalDateTime lastAuthentication;

}
