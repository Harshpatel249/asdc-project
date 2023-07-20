import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './Onboarding.css';

function Onboarding() {
  const [interestList, setInterestList] = useState([]);
  const imagePathPrefix = "src/Assets/Images/";

  useEffect(() => {
    axios.get('https://localhost:8080/interest')
      .then(response => {
        setInterestList(response.data);
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
          {interestList.map((category) => {
            const imageUrl = imagePathPrefix + category.name + '.jpg';

            return (
              <div className="card mb-3 me-2" key={category.id} style={{ width: "24%" }}>
                <img
                  className="card-img-top"
                  src={imageUrl}
                  alt={category.name}
                />
                <div className="card-body">
                  <div className="form-check">
                    <label className="form-check-label">{category.name}</label>
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
