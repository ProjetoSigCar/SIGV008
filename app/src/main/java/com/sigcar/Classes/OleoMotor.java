//package com.sigcar.Classes;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.sigcar.DAO.ConfiguracaoFirebase;
//import com.sigcar.Helper.Base64Custom;
//<<<<<<< Updated upstream
//import com.sigcar.Helper.DataTelas;
//import com.sigcar.Helper.DateCustom;
//
//public class OleoMotor {
//
//
//    private String artistDate;
//    private String artistName;
//    private String artistGenre;
//    private String artistIdade;
////    private double valor;
////    private String key;
//=======
//import com.sigcar.Helper.DataTelas;
//
//public class OleoMotor {
//
//    private String artistData;
//    private String artistName;
//    private String artistIdade;
//    private String tipo;
//    private String key;
//
//
//    public OleoMotor() {
//    }
//
//    public void salvar(String dataEscolhida){
//
//        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
//        String idUsuario = Base64Custom.codificarBase64( autenticacao.getCurrentUser().getEmail() );
//
//        String mesAno = DateCustom.mesAnoDataEscolhida( dataEscolhida );
//
//        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
//        firebase.child("OleoMotor")
//
//        String mesAno = DataTelas.mesAnoDataEscolhida( dataEscolhida );
//
//        DatabaseReference firebase = ConfiguracaoFirebase.getFirebase();
//        firebase.child("oleoMotor")
//
//                .child( idUsuario )
//                .child( mesAno )
//                .push()
//                .setValue(this);
//
//    }
//
////
////    public String getKey() {
////        return key;
////    }
////
////    public void setKey(String key) {
////        this.key = key;
////    }
//
//    public String getData() {
//        return artistDate;
//    }
//
//    public void setData(String data) {
//        this.artistDate = data;
//=======
//
//    public String getKey() {
//        return key;
//    }
//
//    public void setKey(String key) {
//        this.key = key;
//    }
//
//    public String getData() {
//        return artistData;
//    }
//
//    public void setData(String data) {
//        this.artistData = data;
//>>>>>>> Stashed changes
//    }
//
//    public String getCategoria() {
//        return artistName;
//    }
//
//    public void setCategoria(String categoria) {
//        this.artistName = categoria;
//    }
//
//    public String getDescricao() {
//<<<<<<< Updated upstream
//        return artistGenre;
//    }
//
//    public void setDescricao(String descricao) {
//        this.artistGenre = descricao;
//    }
//
//    public String getTipo() {
//        return artistIdade;
//    }
//
//    public void setTipo(String tipo) {
//        this.artistIdade = tipo;
//    }
//
////    public double getValor() {
////        return valor;
////    }
////
////    public void setValor(double valor) {
////        this.valor = valor;
////    }
//=======
//        return artistIdade;
//    }
//
//    public void setDescricao(String descricao) {
//        this.artistIdade = descricao;
//    }
//
//    public String getTipo() {
//        return tipo;
//    }
//
//    public void setTipo(String tipo) {
//        this.tipo = tipo;
//    }
//
//
//>>>>>>> Stashed changes
//}
