package LEXICOX;
/* Sección de declaraciones de JFlex */
%%
%public
%class LexicoSolo
%{
 private boolean _existenTokens = false;
 
 public boolean existenTokens(){
 return this._existenTokens;
 }
 
%}
 

%type TokenPersonalizado
 
%init{

%init}
 
%eof{
 

 this._existenTokens = false;
 
%eof}
 
/* Inicio de Expresiones regulares */
 
 Digito = [0-9]
 Numero = {Digito} {Digito}*
 Letra = [A-Za-z]
 Palabra = {Letra} {Letra}*
 Simbolo = "*"|"+"|"-"|"/"|"#"
 Espacio = " "
 SaltoDeLinea = \n|\r|\r\n
 Id = [:jletter:]["�"|"�"|"�"|"�"|"�"|[:jletterdigit:]|"_"|]*
 Cadena = [\"] [^\"]* [\"]

 Comentario1 = "/*" ["*"]* [^*] ~"*/" | "/*" ["*"]* "*/"
 Comentario2 = "//" [^\r\n]* [^\r\n]
 
/* Finaliza expresiones regulares */
 
%%
/* Finaliza la sección de declaraciones de JFlex */
 
/* Inicia sección de reglas */
 
// Cada regla está formada por una {expresión} espacio {código}
 
{Numero} {
 TokenPersonalizado t = new TokenPersonalizado(yytext(), "NUMERO");
 this._existenTokens = true;
 return t;
}
 

{Id} {
 TokenPersonalizado t = new TokenPersonalizado(yytext(), "ID");
 this._existenTokens = true;
 return t;
}

{Cadena} {
 TokenPersonalizado t = new TokenPersonalizado(yytext(), "CADENA");
 this._existenTokens = true;
 return t;
}
 
{Simbolo} {
 TokenPersonalizado t = new TokenPersonalizado(yytext(), "SIMBOLO");
 this._existenTokens = true;
 return t;
}

{Palabra} {
 TokenPersonalizado t = new TokenPersonalizado(yytext(), "PALABRA");
 this._existenTokens = true;
 return t;
}


{Comentario1} {
 // Ignorar cuando se ingrese un espacio
}
{Comentario2} {
 // Ignorar cuando se ingrese un espacio
}
 
{Espacio} {
 // Ignorar cuando se ingrese un espacio
}
 
{SaltoDeLinea} {
 TokenPersonalizado t = new TokenPersonalizado("Enter", "NUEVA_LINEA");
 this._existenTokens = true;
 return t;
}