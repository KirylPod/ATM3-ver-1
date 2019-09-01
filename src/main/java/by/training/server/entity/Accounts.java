package by.training.server.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Table(name = "Accounts")
public class Accounts {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "account_id")
    private String accountsId;

    @Column(name = "account_pin")
    private String accountsPin;

    @Column(name = "account_cash")
    private int accountsCash;


}
