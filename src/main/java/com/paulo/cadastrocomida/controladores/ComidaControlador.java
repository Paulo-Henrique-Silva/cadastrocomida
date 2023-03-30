package com.paulo.cadastrocomida.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.paulo.cadastrocomida.modelos.Comida;

//static: Coisas estáticas, imagens, estilos, scripts etc.
//templates: Páginas de marcação de texto.

@Controller
public class ComidaControlador {
    
    private List<Comida> comidas = new ArrayList<>();
        
    @GetMapping("/lista")
    public ModelAndView verLista() {
        ModelAndView modelViewListar = new ModelAndView("inicio");
        modelViewListar.addObject("comidas", comidas);

        return modelViewListar; 
        //return "inicio"; //retorna a página html na pasta templates com o nome da string
    }

    @GetMapping("/editar_comida/{id}")
    public ModelAndView verFormEdicao(@PathVariable long id) {
        Comida comida = comidas.get((int)id - 1);

        ModelAndView modelViewEditar = new ModelAndView("editar_comida");
        modelViewEditar.addObject("comida", comida);

        return modelViewEditar; 
    }

    //ModelAndView: Permite retornar uma view e um modelo ao mesmo tempo.
    
    @PostMapping("/lista")
    public ModelAndView adicionarComida(Comida comida) {
        if (comida.getId() == 0) { //verifica se é edição.
            comida.setId(comidas.size() + 1); //cria um id para a comida.

            comidas.add(comida); //adiciona à lista.
        } else {
            comidas.set((int)comida.getId() - 1, comida); //atualiza a comida
        }

        ModelAndView modelView = new ModelAndView("inicio");
        modelView.addObject("comidas", comidas);

        return modelView; 
        //return "redirect:/comidas" //redireciona ao endpoint GET de /comidas. Dessa forma, garante que a view seja "reiniciada" para um novo ciclo de requisições.
    }

    @GetMapping("/deletar_comida/{id}")
    public ModelAndView deletarComida(@PathVariable long id) {
        comidas.remove(comidas.indexOf(comidas.get((int)id - 1)));

        ModelAndView modelView = new ModelAndView("redirect:/lista");
        modelView.addObject("comidas", comidas);

        return modelView;
    }
}
