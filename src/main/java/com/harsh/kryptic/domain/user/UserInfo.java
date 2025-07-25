package com.harsh.kryptic.domain.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.harsh.kryptic.utils.Utils;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity(name = Utils.USERS_TABLE)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    Long id;
    String username;
    String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-dd-mm")
    Date dob;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String accessToken;
}
