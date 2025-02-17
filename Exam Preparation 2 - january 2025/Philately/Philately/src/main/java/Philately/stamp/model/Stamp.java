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
public class Stamp {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

//      •	Has a Name (not null)
//    Name length must be between 5 and 20 characters (inclusive of 5 and 20).

//            •	Has a Description (not null) (a brief description of what the stamp depicts)
//    Description length must be between 5 and 25 characters (inclusive of 5 and 25)

//              •	Has a Paper (not null) (the type of paper on which the stamp is printed) enumeration with values:
//    o	For WOVE_PAPER – "Has an even texture without any particular distinguishing features."
//    o	For LAID_PAPER – "When held up to the light, shows parallel lines of greater or less width running across the stamp."
//    o	For GRANITE_PAPER – "Has tiny specks of coloured fibre in it, which can usually be seen with the naked eye."

//            •	Has an Image URL (not null)
//    Valid image URL containing no more than 150 characters.

//          •	Has an Owner
//    A stamp has one Owner, the User who added it.


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Paper paper;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne(optional = false)
    private User owner;

}
