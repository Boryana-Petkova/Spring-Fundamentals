package Philately.stamp.model;

import Philately.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class WishedStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

//          •	Has a Name (not null)
//    Name length must be between 5 and 20 characters (inclusive of 5 and 20).

//            •	Has a Description (not null) (a brief description of what the stamp depicts)
//    Description length must be between 5 and 25 characters (inclusive of 5 and 25)

//              •	Has an Image URL (not null)
//    Valid image URL containing no more than 150 characters.

//              •	Has an Owner – who is owning this wished stamp.

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne(optional = false)
    private User owner;

}
