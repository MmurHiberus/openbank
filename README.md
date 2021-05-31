 **INTRODUCCIÓN**
El tipo de proyecto es un listado-detalle usando los personajes de la API publica de Marvel.

Consta de un activity que hace de marco de 2 fragmentos:

-   **CharacterListFragment**
    
    -   Fragmento de listado de personajes
        
    -   Al lanzar el fragmento hace una petición a la API de Marvel, con un deserializer personalizado convierte datos de json a objeto y los inserta en BD en 2º plano.
        
    -   El fragmento observa una query en su BD que consulta el listado de personajes con sus ítems, si cambia un ítem o personaje en BD se reflejará automáticamente en la UI.
        
    -   Imágenes en el listado cargadas con Glide a través de URLs.
        
    -   Clic en un ítem del listado: Navega a detalles del personaje.
        
    -   Filtro en directo, si cambias el texto del filtro notificará al listado y cambiará automáticamente.
        
-   **AboutUsFragment**
    
    -   Fragmento de información, logo de Hiberus y programador del proyecto
        
    -   No exigido en la prueba pero añadido para enseñar el uso de BottomNavigationView (menú de navegación de abajo)
        

  

Fragmento de detalles del personaje (**CharacterDetailFragment**):

-   Accedes a el al hacer clic en un item del listado de **CharacterListFragment**
    
-   Contiene un cardview con el nombre y un viewPager 2 con 2 fragmentos, info e ítems
    
-   El viewpager permite scroll lateral y clic en sus tabs.
    
-   Fragmento INFO (CharacterDetailInfoFragment)
    
    -   Imagen del personaje, cargada con glide
        
    -   Links que lanzan el navegador con detalles, wiki y cómic si los tuviese
        
    -   Descripción y fecha, si los tuviese
        
-   Fragment ITEMS (CharacterDetailItemsFragment)
    
    -   Contiene un taglayout a modo de filtro y un listado de ítems, con todos los ítems
        
    -   Los ítems vienen en el JSON de su descarga en distintos arrays (comics, stories…) y son unificados con una propiedad tipo para diferenciarlos.
        
    -   El taglayout es una librería externa
        
        -   Consiste en un contenedor donde insertas tags clicables que puedes seleccionar/deseleccionar
            
        -   Los tags son insertados manualmente en cada detalle dependiendo de los tipos de items que tenga un personaje, por ejemplo si no tiene cómics ese tag no estará, tendrá solo events, series y stories
            
        -   Tiene un listener para cuando seleccionas/de-seleccionas un tag y con eso notifica al listado de items para filtrarlos.
            

  

  

**ARQUITECTURA Y COMPONENTES**

  

**Arquitectura**

  

-   Arquitectura MVVM (Model, View, Viewmodel) con databinding
    
-   Activity que contiene fragmentos (View) y vinculan (databinding) el viewmodel en su vista XML.
    
-   MainViewModel, clase con acciones (clics) y lógica de negocio si hicicese falta. Contiene los modelos de datos que usa.
    
-   Model, modelos de datos observables
    
    -   MainModelMenuModel: Modelo de datos principal
        
    -   CharacterMainModel: Modelo de datos específico del detalles
        
    -   MenuModel: Modelo de datos específico del menú superior.
        

  

**Por qué varios modelos de datos (Model)**

  

Podría estar todo en el MainModel pero sólo dificultaría el mantenimiento/ampliación del proyecto.

Hay datos específicos de las vistas hijo (detalles del personaje, por ejemplo) que sólo se usan ahí, no tiene sentido llevárselos al resto de la app y sobrecargarla de datos.

  

**Databinding**

  

El databinding es vincular objetos y propiedades de clases observables kotlin a las vistas XML, de tal forma que si cambias un valor notificará de forma automáticamente la vista.

Se usa en todo el proyecto salvo en los adapters del listado, al ser vistas creadas dinámicamente afecta al rendimiento y se ha usado el patrón Holder.

  

  

**Base de datos**

  

Base de datos con Android Room, ORM basado en SQLite. (Librería nativa)

  

  

**Consumo de API Rest**

  

Consumo de API Rest con Retrofit2 (Librería externa)

