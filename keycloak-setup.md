- Stwórz Realm o nazwie training

- Stwórz nowego clienta
    - Client ID: products-backend
    
- Skonfiguruj klienta
    - training->Clients->Settings->products-backend
        - Valid Redirect URIs: http://localhost:8080/*
        - Base URL: http://localhost:8080
        
- Stwórz role
    - training->Roles
        - Role name: user
        
    - training->Roles
        - Role name: admin
        
- Stwórz użytkowników
    - training->Users
        - Username: client
        
    - training->Users
        - Username: employee  
          
- Skonfiguruj użytkowników
    - training->Users->client->Credentials
        - Password: 123
        - Password Confirmation: 123
        - Temporary: off    
 
    - training->Users->client->Role Mappings
        - Assigned Roles: user   
        
    - training->Users->employee->Credentials
        - Password: 123
        - Password Confirmation: 123
        - Temporary: off   
         
    - training->Users->employee->Role Mappings
        - Assigned Roles: admin       
      
- Skonfiguruj możliwość rejestracji użytkowników
    - training->Realm settings->Login/Email
    
- Ustaw motyw dla ekranu logowania
    - training->Realm settings->Themes
        - Login Theme: training-theme
            
- Skonguruj dostawców tożsamości (uwaga na localhost)
    - w ustawieniach konta GitGub: Settings->Developer settings->GitHub Apps
        - Homepage URL: http://localhost:8000/auth/realms/training
        - User authorization callback URL: http://localhost:8000/auth/realms/training/broker/github/endpoint
        - Permissions: Email addresses / read-only
        
    - training->Identity Providers->GitHub
        - Client ID: xxxxx
        - Client Secret: xxxxx
    
    - training->Identity Providers->github->Mappers
        - Name: role
        - Mapper Type: Hardcoded Role
        - Role: admin
       
    - w konsoli https://console.developers.google.com Dane logowania->Identyfikatory klienta OAuth 2.0
        - Identyfikatory URI: http://localhost:8000/auth/realms/training/broker/google/endpoint
       
    - training->Identity Providers->Google
        - Client ID: xxxxx
        - Client Secret: xxxxx
    
    - training->Identity Providers->Google->Mappers
        - Name: role
        - Mapper Type: Hardcoded Role
        - Role: admin
        
- Skonfiguruj ustawienia dla Angular
    - training->Clients->Settings->products-backend
        - Valid Redirect URIs: http://localhost:4200/*
        - Web Origins: http://localhost:4200

- Skonfiguruj ustawienia dla ReactJS
    - training->Clients->Settings->products-backend
        - Valid Redirect URIs: http://localhost:3000/*
        - Web Origins: http://localhost:3000

- Skonfiguruj uwierzytelnianie 2 poziomowe
    - training->Authentication->Required Actions (dla nowych użytkowników) 
        - Configure OTP->Default Action=true  
               
    - training->Users->employee->Details
        - Required User Actions: Configure OTP 