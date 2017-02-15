/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagnie.nppwebmaven.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mycompagnie.nppwebmaven.jdbc.Connexion;
import com.mycompagnie.nppwebmaven.mapping.Login;
import com.mycompagnie.nppwebmaven.mapping.Utilisateur;
import java.io.File;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

/**
 *
 * @author mbola
 */
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB
public class Inscription extends HttpServlet {

    private final String UPLOAD_DIRECTORY = "upload";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
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
        response.setContentType("application/json;charset=UTF-8");
        
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "86400");
        
        // debut micreer anilay dossier asina anilay fichier raha tsy misy
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + UPLOAD_DIRECTORY;
        // fin micreer anilay dossier asina anilay fichier raha tsy misy
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        Part filePart = request.getPart("photo");
		//maka anaranle fichier
        String fileName = extractFileName(filePart);
        fileName = new File(fileName).getName();
		//
		//mienregistre anilay fichier
        filePart.write(savePath+File.separator+fileName);
		//
		
		//url = upload/fileName
        String url_photo = "upload/"+fileName;
        
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confPassword = request.getParameter("confpassword");
        String email = request.getParameter("email");
        Connection c = null;
        try {
            c = Connexion.getConnection();
            if(password.compareTo(confPassword)==0){
                long idu = Utilisateur.getLastIndex();
                long idl = Login.getLastIndex();
                Utilisateur u = new Utilisateur(idu,lastname,name);
                Login l = new Login(idl,idu , username, password, email, url_photo);
                if(u.setUtilisateur(c)) {
                    if(l.setLogin(c)) {
                        c.commit();
                        c.close();
                        response.sendRedirect("index.html");
                        
                    }
                    else {
                        c.rollback();
                        c.close();
                        response.sendRedirect("inscription.html?error=in");
                    }
                }
                else {
                    c.rollback();
                    c.close();
                    response.sendRedirect("inscription.html?error=in");
                }
                
        }
        } catch(Exception e) {
            System.out.println(e);
        }
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
        response.setContentType("application/json;charset=UTF-8");
        
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "86400");
        
        // debut micreer anilay dossier asina anilay fichier raha tsy misy
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + UPLOAD_DIRECTORY;
        // fin micreer anilay dossier asina anilay fichier raha tsy misy
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        Part filePart = request.getPart("photo");
		//maka anaranle fichier
        String fileName = extractFileName(filePart);
        fileName = new File(fileName).getName();
		//
		//mienregistre anilay fichier
        filePart.write(savePath+File.separator+fileName);
		//
		
		//url = upload/fileName
        String url_photo = "upload/"+fileName;
        
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confPassword = request.getParameter("confpassword");
        String email = request.getParameter("email");
        Connection c = null;
        try {
            c = Connexion.getConnection();
            if(password.compareTo(confPassword)==0){
                long idu = Utilisateur.getLastIndex();
                long idl = Login.getLastIndex();
                Utilisateur u = new Utilisateur(idu,lastname,name);
                Login l = new Login(idl,idu , username, password, email, url_photo);
                if(u.setUtilisateur(c)) {
                    if(l.setLogin(c)) {
                        c.commit();
                        c.close();
                        response.sendRedirect("index.html");
                        
                    }
                    else {
                        c.rollback();
                        c.close();
                        response.sendRedirect("inscription.html?error=in");
                    }
                }
                else {
                    c.rollback();
                    c.close();
                    response.sendRedirect("inscription.html?error=in");
                }
                
        }
        } catch(Exception e) {
            System.out.println(e);
        }
            
        
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
