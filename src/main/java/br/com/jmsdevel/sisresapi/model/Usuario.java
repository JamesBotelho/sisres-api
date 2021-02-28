package br.com.jmsdevel.sisresapi.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.jmsdevel.sisresapi.dto.UsuarioDto;

@Entity
public class Usuario implements UserDetails {
	
	private static final long serialVersionUID = 4754503914233813389L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true, length = 10)
	private String username;
	@Column(nullable = false)
	private String senha;
	@Column(nullable = false, length = 100)
	private String nome;
	@Column(nullable = false, length = 100)
	private String email;
	@Column(nullable = false, length = 11)
	private String telefone;
	@Column(nullable = false)
	private LocalDate dataNascimento;
	@OneToMany(mappedBy = "usuario")
	private List<Reserva> reservas;
	@ManyToMany
	private List<Perfil> perfis;
	
	public Usuario() {}
	
	public Usuario(UsuarioDto usuarioDto, DateTimeFormatter formatter) {
		this.username = usuarioDto.getUsername();
		this.nome = usuarioDto.getNome();
		this.email = usuarioDto.getEmail();
		this.telefone = usuarioDto.getTelefone();
		this.dataNascimento = LocalDate.parse(usuarioDto.getDataNascimento(), formatter);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return perfis;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
