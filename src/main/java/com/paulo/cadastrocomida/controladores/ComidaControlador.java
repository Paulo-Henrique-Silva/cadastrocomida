package com.paulo.cadastrocomida.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.paulo.cadastrocomida.modelos.Comida;

//static: Coisas estáticas, imagens, estilos, scripts etc.
//templates: Páginas de marcação de texto.

@Controller
@RequestMapping("/comidas")
public class ComidaControlador {
    
    private List<Comida> comidas = new ArrayList<>();

   /*  @GetMapping("")
    public String verLista(){
        return "inicio"; ///retorna a página html na pasta templates com o nome da string
    } */

    
    @GetMapping("")
    public String verLista() {
        return "inicio"; ///retorna a página html na pasta templates com o nome da string
    }

    //ModelAndView: Permite retornar uma view e um modelo ao mesmo tempo.
    
    @PostMapping("")
    public ModelAndView adicionarComida(Comida comida) {
        comida.setId(comidas.size() + 1); //cria um id para a comida.
        comidas.add(comida); //adiciona à lista.

        ModelAndView modelView = new ModelAndView("inicio");
        modelView.addObject("comidas", comidas);

        return modelView; //redireciona ao endpoint GET de /comidas. Dessa forma, garante que a view seja "reiniciada" para um novo ciclo de requisições.
    }

    @PutMapping("")
    public ModelAndView editarComida(Comida comida) {
        Comida comidaAntiga = comidas.get((int)comida.getId());

        comidaAntiga.setNome(comida.getNome());
        comidaAntiga.setPreco(comida.getPreco());

        ModelAndView modelView = new ModelAndView("inicio");
        modelView.addObject("comidas", comidas);

        return modelView; //redireciona ao endpoint GET de /comidas. Dessa forma, garante que a view seja "reiniciada" para um novo ciclo de requisições.
    }
}
