import React, { useEffect, useState } from 'react';
import './Onboarding.css';
import image from '../../Assets/Images/category.jpg';

function Onboarding() {
  const [interestList, setInterestList] = useState([]);
  const imagePathPrefix = "src/Assets/Images/";

  useEffect(() => {
    fetch('https://commune-dev-csci5308-server.onrender.com/interest')
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        console.log(data);
        setInterestList(data);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
  }, []);

  return (
    <>
      <div>
        <header className="bg-lightblue p-2">
          <h3>Select at least 3 categories.</h3>
        </header>
        <div className="container d-flex justify-content-start flex-wrap mt-2">
          {interestList.map((interest) => {

            return (
              <div className="card mb-3 me-2" key={interest.interestId} style={{ width: "24%" }}>
                {/* <img
                  className="card-img-top"
                  src={imageUrl}
                  alt={category.name}
                /> */}
                <img src= {image} />
                <div className="card-body">
                  <div className="form-check">
                    <label className="form-check-label">{interest.interestName}</label>
                    <input className="form-check-input" type="checkbox" style={{ width: "1.5rem", height: "1.5rem" }} />
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </>
  );
}

export default Onboarding;
