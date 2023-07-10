import React from 'react';
import { Outlet } from 'react-router-dom';
import './Onboarding.css';

function Onboarding() {
  const imagePathPrefix = "src/Assets/Images/";
  const categories = [
    { id: 1, name: "Category 1", imageUrl: "category.jpg" },
    { id: 2, name: "Category 2", imageUrl: "category.jpg" },
    { id: 3, name: "Category 3", imageUrl: "category.jpg" },
    { id: 4, name: "Category 4", imageUrl: "category.jpg" },
    { id: 5, name: "Category 5", imageUrl: "category.jpg" },
    { id: 1, name: "Category 1", imageUrl: "category.jpg" },
    { id: 2, name: "Category 2", imageUrl: "category.jpg" },
    { id: 3, name: "Category 3", imageUrl: "category.jpg" },
    { id: 4, name: "Category 4", imageUrl: "category.jpg" },
    { id: 5, name: "Category 5", imageUrl: "category.jpg" }
  ];

  return (
    <>
      <div>
        <header className="bg-lightblue p-2">
          <h3>Select at least 3 categories.</h3>
        </header>
        <div className="container d-flex justify-content-start flex-wrap mt-2">
          {categories.map((category) => (
            <div className="card mb-3 me-2" key={category.id} style={{ width: "24%" }}>
              <img
                className="card-img-top"
                src={imagePathPrefix + category.imageUrl}
                alt={category.name}
              />
              <div className="card-body">
                <div className="form-check">
                  <label className="form-check-label">{category.name}</label>
                  <input className="form-check-input" type="checkbox" style={{ width: "1.5rem", height: "1.5rem" }} />
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
      <Outlet />
    </>
  );
}

export default Onboarding;
