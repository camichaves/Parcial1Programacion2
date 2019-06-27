package Personas.BaseDatos;



import java.util.ArrayList;
import java.util.List;

import dao.IPersonaDAO;
import dao.IProductoDAO;
import entidades.Persona;
import entidades.Producto;
import entidades.Venta;
import servicios.AccesoBD;
import servicios.PersonaDAOImplem;
import servicios.ProductoDAOImplem;
import servicios.Vender;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
    	
        
        //Conecto a la base de datos
        AccesoBD acc = new AccesoBD("root","");
        acc.Conectar();
        
        
        IPersonaDAO pDAO =  new PersonaDAOImplem();
        IProductoDAO prDAO = new ProductoDAOImplem();
        
        
        
        //prueba de que se puede usar o no el activo
        Persona p1= new Persona("Valentina","Herrero",4353342);
        boolean act=false;
        Persona p2 = new Persona("Annie","Colvin",405936475,act);
        pDAO.insertar(p1);
        pDAO.insertar(p2);
        //se guarda como NULL en la base de datos los que no lo usan,
        // y 0 o 1 dependiendo si estan activados o desactivados.
        
        //tambien podemos activar a alguien
        p2.activar();
        pDAO.actualizar(p2);
        
        
        
      //Prueba de mostrar documento
        System.out.println(p2.mostrardocumento());
        System.out.println(p1.mostrardocumento());
        
        
        //Prueba de FindAllActivos
        pDAO.findAllActivos(); //este metodo muestra por pantalla y retorna un List con todos los activos.
        
        
        
        //Prueba de findByNombreOrApellidoLike
        pDAO.findByNombreOrApellidoLike("Ca"); //este metodo muestra por pantalla y retorna un List.
        
        
        
        Producto pr1 = new Producto("Cartuchera","grande",(float) 70);
        
        
        
      //prueba de insertar y borrar
        
        
        prDAO.insertar(pr1);
        prDAO.borrar(pr1);
        
        
        
        
        Producto pr2 = new Producto("Alimento","premmium", (float)400);
        Persona p3 = new Persona("Emma", "Tsai", 292387432,false);
        Persona p4 = new Persona("Minami","Ito",3565233,true);
        pDAO.insertar(p3);
        pDAO.insertar(p4);

        
        List<Producto> productos = new ArrayList<Producto>();
        productos.add(pr2);
        productos.add(pr1);
        
        boolean vta=false;
        
        vta=Vender.newVenta(p3, "vendo con persona desactivada EMMA",productos);
        if(vta) {System.out.println("Venta exitosa");}else {System.out.println("Venta fallida");}
        
        vta=Vender.newVenta(p4, "vendo con persona activada MINAMI",productos);
        if(vta) {System.out.println("Venta exitosa");}else {System.out.println("Venta fallida");}
        
        acc.CerrarConexion();
        
       
    }
}
