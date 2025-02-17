package Philately.user.model;

import Philately.stamp.model.Stamp;
import Philately.stamp.model.WishedStamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity

public class User {


        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        /*•	Has a Username (unique, not null)
    Username length must be between 3 and 20 characters (inclusive of 3 and 20).

            •	Has a Password (not null)
    Password length must be between 3 and 20 characters (inclusive of 3 and 20).

            •	Has an Email (unique, not null)
    Must contain '@'.

            •	Has a List<Stamp> list of the added stamps
            •	Has a List<WishedStamp> list of the wished stamps*/



    @Column(unique = true, nullable = false)
        private String username;

        @Column(nullable = false)
        private String password;

        @Column(unique = true, nullable = false)
        private String email;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Stamp> stamps;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<WishedStamp> wishedStamps;


}
