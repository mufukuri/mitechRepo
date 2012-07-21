package zw.co.mitech.mtutor.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-07-16T04:47:37.111+0200")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> ownerType;
	public static volatile SingularAttribute<User, String> ownerId;
	public static volatile SingularAttribute<User, String> role;
	public static volatile SingularAttribute<User, Long> version;
}
