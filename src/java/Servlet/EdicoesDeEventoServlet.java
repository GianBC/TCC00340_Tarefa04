/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Entidades.Edicao;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Gianluca Bensabat Calvano
 */
@WebServlet(name = "EdicoesDeEventoServlet", urlPatterns = {"/edicoesdeeventoservlet"})
public class EdicoesDeEventoServlet extends HttpServlet {

    WebTarget web_target;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //List<Edicao> lista_edicao = null;
        //Evento evento=null;
        
        try (PrintWriter out = response.getWriter()) {
            
            Client client = ClientBuilder.newClient();
            URI uri;

            try {
                String uri_base ="http://localhost:8080/tarefa03/webresources/service/evento/";
                String uri_variavel = (request.getParameter("id_evento")+"/edicoes");
                //uri = new URI (uri_base+uri_variavel);
                uri = new URI ("http://localhost:8080/tarefa03/webresources/service/evento/2/edicoes");
                this.web_target = client.target(uri);
                web_target.request().accept(MediaType.APPLICATION_XML);
                Invocation call = web_target.request().buildGet();
                Response resp = call.invoke();
                int status_resp = resp.getStatus();
                List<Edicao> lista_edicao = resp.readEntity(new GenericType<List<Edicao>>(){});
                
                /*String base = "http://localhost:8080/AgendaREST/resources/agenda/sobrenomes/";
                String link = base+request.getParameter("sobrenome");
                uri = new URI(link);
                this.wt = client.target(uri);
                wt.request().accept("application/xml");
                Invocation call = wt.request().buildGet();
                Response resposta = call.invoke();
                status = resposta.getStatus();
                lista = resposta.readEntity(new GenericType<List<Entrada>>(){});
                
                uri = new URI("http://localhost:8080/tarefa03/webresources/service/evento");
                this.web_target = client.target(uri);
                web_target.request().accept(MediaType.APPLICATION_XML);
                Invocation call = web_target.request().buildGet();
                Response resp = call.invoke();
                int status_resp = resp.getStatus();
                lista_evento = resp.readEntity(new GenericType<List<Evento>>(){});*/
                
            } catch (URISyntaxException ex) {
                Logger.getLogger(TodosEventosServlet.class.getName()).log(Level.SEVERE, null, ex);
            }/*

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Todos os eventos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Lista de Eventos:</h2>");
            out.println("<ul>");

            Iterator<Edicao> EdicaoAsIterator = lista_edicao.iterator();
            while (EdicaoAsIterator.hasNext()) {
                Edicao edicao_itr = EdicaoAsIterator.next();
                out.println("<h3>Número: "+edicao_itr.getNumero()+"</h3>");
                out.println("<h3>Ano: "+edicao_itr.getAno()+"</h3>");
                out.println("<h3>Cidade: "+edicao_itr.getCidade()+"</h3>");
                out.println("<h3>País: "+edicao_itr.getPais()+"</h3>");
                out.println("<h3>Data inicial: "+edicao_itr.getDataini()+"</h3>");
                out.println("<h3>Data final: "+edicao_itr.getDatafim()+"</h3>");
                out.println("<h3>ID no Banco de Dados: "+edicao_itr.getId()+"</h3>");
            }*/
            out.println("</ul>");
            out.println(" <h4><a href=\"http://localhost:8080/tarefa04\">Página inicial</a></h4>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
