import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; 
import './login.css'
import image from '../../Assets/Images/loginimg.png';

 function Login (){

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(false);
    const [passErrorMsg, setPassErrorMsg] = useState('');
    const [emailErrorMsg, setEmailErrorMsg] = useState('');
    const navigate = useNavigate();
    const regEx = /[a-zA-Z0-9._-]+@[a-z0-9]+\.[a-z]{2,8}(.[a-z{2,8}])?/g; 
    const passwordRegex=/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d!@#$%^&*()_+]{8,}$/;
    
    const onLogin = () => {
      
      if(error===true || email===""|| password==="")
      alert("Please enter the valid credentials")
      else
      {
        const loginData = {
          email,
          password,
        };
        fetch('http://localhost:8080/users/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(loginData),
      })
        .then((response) => response.json())
        .then((data) => {
          if (data) {
            console.log(data);
            localStorage.setItem('BearerToken', data.bearerToken);
            localStorage.setItem('userID', data.userId);
            localStorage.setItem('fullName', data.name);
            alert('Welcome, You have successfully logged in');
            setEmail('');
            setPassword('');
            window.location.href = '/';
          } else {
            alert('Login failed. Please check your credentials.');
          }
        })
        .catch((error) => {
          console.error('Error:', error);
          alert('An error occurred during login. Please try again later.');
        });
      }
    };

    const onEmailBlur =() => {
      if(!regEx.test(email) && email!=="")
      {
              setError(true);
              setEmailErrorMsg("Please enter a valid email address");
      }
      else
      {
      setError(true);
      setEmailErrorMsg("");
      }

    }

    const onPasswordBlur = () => {
      if(!passwordRegex.test(password) && password!=="")
      {
        setError(true);
        setPassErrorMsg("Password must contain 1 uppercase<br>Must contain lowercase <br> Must contain special charcter <br> minimum length of 8 characters");
      }
      else
      {
        setError(false);
        setPassErrorMsg('');
      }
    };

    const handleForgotPassword = () => {
      if(email==="" || email===null || emailErrorMsg===true)
      {
        setEmail("");
        alert("Please enter the registered email ");
      }
      else{
        fetch(`http://localhost:8080/users/forgotPassword?email=${email}`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: '',
        })
          .then((response) => response.text())
          .then((code) => {
            console.log(code);
            console.log(email);
            navigate('/reset', { state: { code , email} });
          })
          .catch((error) => {
            console.error('Error:', error);
            alert('An error occurred during the  process. Please try again later.');
          });
      }
    };

    return (
      <div className="App">
        <header className="App-header">
        <div className='loginForm'>
        <h1 className='text-dark'>Login Screen</h1>
        <form>
          <div style={{display:"flex",justifyContent:"center"}}>
  
          <img src={image} alt='loginImage' width="100" height="100" />
          </div>
            <input type="email" placeholder='Enter Email' value={email} onChange={(e) => setEmail(e.target.value)} onBlur={onEmailBlur} />
            <i dangerouslySetInnerHTML={{ __html: emailErrorMsg }} />
          <div>
            <input type="password" placeholder='Enter Password' value={password} onChange={(e) => setPassword(e.target.value)} onBlur={onPasswordBlur} />
            <i dangerouslySetInnerHTML={{ __html: passErrorMsg }} />
          </div>
          
          <div style={{ display: 'flex', alignItems: 'center', justifyContent:"space-around" }}>
            <button className='btn' type="button" onClick={onLogin}>Login</button>
            
            </div>
            <div className='d-flex justify-content-start mt-2'>
              <div className='d-flex'>
              <a href="/signup"  style={{ marginLeft: '10px' }} >
              Create an account
              </a>
              </div>
              <div className='d-flex'>
              <a href="#" onClick={handleForgotPassword}  style={{ marginLeft: '10px' }} >
              Forgot Password?
            </a>
              </div>
            </div>
            
        </form>
      </div>
        </header>
      </div>
    );  
} export default Login ;