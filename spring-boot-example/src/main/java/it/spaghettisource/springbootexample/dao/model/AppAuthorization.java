package it.spaghettisource.springbootexample.dao.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class AppAuthorization {
	
    @Id
	@SequenceGenerator(name="app_authorization_sequence",sequenceName = "app_authorization_sequence",allocationSize = 1,initialValue = 100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "app_authorization_sequence")
    private Long id;
    
    public AppAuthorization() {
		super();
	}
    
	public AppAuthorization(Long id, String permission) {
		super();
		this.id = id;
		this.permission = permission;
	}


	@Column(nullable = false)
    private String permission;

    @ManyToMany(mappedBy = "authorizations")
    private Set<AppRole> roles = new HashSet<>();
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
//	public Set<Role> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Set<Role> roles) {
//		this.roles = roles;
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(id, permission, roles);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Authorization other = (Authorization) obj;
//		return Objects.equals(id, other.id) && Objects.equals(permission, other.permission)
//				&& Objects.equals(roles, other.roles);
//	}
//
//	@Override
//	public String toString() {
//		return "Authorization [id=" + id + ", permission=" + permission + "]";
//	}

}
