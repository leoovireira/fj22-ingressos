package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;

/**
 * Created by nando on 03/03/17.
 */
@Entity
public class Filme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Duration duracao;
	private String genero;

	private BigDecimal preco = BigDecimal.ZERO;

	/**
	 * @deprecated hibernate only
	 */
	public Filme() {

	}

	public Filme(String nome, Duration duracao, String genero, BigDecimal preco) {
		this.nome = nome;
		this.duracao = duracao;
		this.genero = genero;
		this.preco = preco;
	}

	public BigDecimal getPreco() {
		return preco.setScale(2, RoundingMode.HALF_UP);
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Filme(String nome, Duration duracao, String genero) {
		this.nome = nome;
		this.duracao = duracao;
		this.genero = genero;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Duration getDuracao() {
		return duracao;
	}

	public void setDuracao(long duracao) {
		this.duracao = Duration.ofMinutes(duracao);
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@GetMapping("/filme/em-cartaz")
	public ModelAndView emCartaz() {
		FilmeDao filmeDao = new FilmeDao();
		ModelAndView modelAndView = new ModelAndView("filme/em-cartaz");
		modelAndView.addObject("filmes", filmeDao.findAll());
		return modelAndView;
	}
}
