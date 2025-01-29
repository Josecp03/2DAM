    <style>
        * {
            box-sizing: border-box;
            padding: 0;
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }
        body {
            overflow: hidden;
        }
        a {
            
            text-decoration: none;
        }

        /* HEADER */
        header {
            background-color: rgb(179, 81, 81);
            color: #FFF;
            font-weight: bold;
            font-size: 1em;
            padding-left: 20px;
            display: flex;
            justify-content: left;
            align-items: center;
            width: 100%;
            height: 55px;
        }
        i {
            margin-right: 6px;
        }

        /* MAIN */
        main {
            height: calc(100vh - 110px);
            min-width: 100%;
            display: flex;
        }
        nav {
            height: 100%;
            width: 200px;
            background-color: #ebebeb;
            border-right: 1px solid rgb(196, 196, 196);
        }
        nav > div {
            background-color: #FFF;
            border: 1px solid rgb(196, 196, 196);
            border-right: 0;
            font-size: 0.9em;  
            height: 35px;
            display: flex;
            align-items: center;
            padding-left: 12px;
        }
        nav > div a {
            color: #363636;
            transition: .25s ease;
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: left;
            align-items: center;
        }
        nav > div a:hover {
            letter-spacing: 1px;
        }

        main > div {
            color: #363636;
            width: 100%;
            height: 100%;
        }

        .cabecera-web {
            width: 100%;
            height: 50px;
            font-weight: bold;
            color: #616161;
            display: flex;
            justify-content: left;
            position: relative;
            align-items: center;
            padding-left: 15px;
            background-color: #EEE;
            border-bottom: #dadada 1px solid;
            border-top: #dadada 1px solid;
        }
        .contenido-tabla {
            padding: 30px;
        }
        table {
            width: 100%;        
            font-size: 0.9em;   
            border-collapse: collapse; 
        }
        tr {
            display: flex;
            width: 100%;
            height: 35px;
        }
        th {
            background-color: #ebebeb;
            cursor: pointer;        
        }
        th, td {
            display: flex;
            justify-content: left;
            align-items: center;
            padding-left: 10px; 
            width: 100%;
            border: 1px solid #cacaca;               
        }
        th:hover {
            color: rgb(35, 108, 243);
        }

        /* FOOTER */
        footer {
            height: 55px;
            width: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: rgb(228, 228, 228);
            color: #363636;
            font-style: italic;
            border-top: 2px solid rgb(196, 196, 196);
        }   
        
        /* FORMULARIOS */        
        .formularios {        
            padding: 30px;            
        }
        .formularios > div {
            width: 100%;
            padding-bottom: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            background-color: #eee;
        }
        .titulo-formulario {
            background-color: rgb(179, 81, 81);            
            display: flex;
            justify-content: center;
            align-items: center;
            width: 100%;        
            height: 40px;    
            margin-bottom: 23px;
        }
        .formularios h4 {
            color: #FFF;
            text-transform: uppercase;            
            font-size: 1.5em;
        }
        form {
            display: flex;
            flex-direction: column;
            gap: 20px;
            width: 400px;
        }
        form > div {
            display: flex;
        }
        label {
            padding-right: 20px;
        }
        [type*="text"] {
            background-color: transparent;
            border: 0;
            border-bottom: 2px solid rgb(179, 81, 81);            
            width: 100%;
            font-family: Arial, Helvetica, sans-serif;
            height: 20px;
        }
        [type*="text"]:focus {
            outline: 0;
            font-family: Arial, Helvetica, sans-serif;
        }
        [type*="text"]::placeholder {
            font-family: Arial, Helvetica, sans-serif;
            color: rgb(179, 81, 81);
            font-size: 1.1em;
        }
        select {
            background-color: transparent;
            font-family: Arial, Helvetica, sans-serif;
            border: 2px solid #383838;
            padding: 5px;
            width: 100%;
        }
        select:focus {
            outline: 0;
        }
        button {
            height: 45px;
            margin-top: 10px;
            background-color: rgb(179, 81, 81);
            color: #FFF;
            font-size: 1.05em;
            border: 0;
            font-weight: bold;
            position: relative;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 5px 10px;
            gap: 4px;
            left: 50%;
            cursor: pointer;
            transform: translateX(-50%);
        }
        
        /* IMPRIMIR */
        .boton-imprimir {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100%;
            cursor: pointer;
            aspect-ratio: 1/1;
            border: 1px solid #cacaca;
            font-size: 1.35em;
            position: absolute;
            right: 0px;
        }
        .boton-imprimir i {
            margin: 0;
        }
    </style>