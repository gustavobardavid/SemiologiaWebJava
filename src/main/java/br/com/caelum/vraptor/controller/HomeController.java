package br.com.caelum.vraptor.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.dao.PacienteDAO;
import br.com.caelum.vraptor.dao.PacienteTestDAO;
import br.com.caelum.vraptor.dao.UsuarioDAO;
import br.com.caelum.vraptor.model.Paciente;

@Path("")
@Controller
public class HomeController {
	
	@Inject UsuarioDAO daoUsuario;
	@Inject PacienteDAO dao;
	@Inject PacienteTestDAO daoTest;
	@Inject Result result;
	
	@Get("")
	public void home() {
		//busca os pacientes no banco
		List<Paciente> pacientes = daoTest.findAll();
		int contadorPacientes = 0;
		int contadorAlcançado = 0;
		int contadorPreocupante = 0;
		//conta quantos pacientes existem com id ativo(softdelete)
		for (Paciente paciente : pacientes) {
		if(paciente.isAlcançado() == true) {
				contadorAlcançado++;
		}
		contadorPacientes++;
		}
		result.include("contadorPacientes", contadorPacientes);
		result.include("contadorAlcançado", contadorAlcançado);
		result.include("contadorPreocupante", contadorPreocupante);	   
	}
	
}
