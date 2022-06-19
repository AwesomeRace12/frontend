import React, { Component } from 'react';
import axios from 'axios'
import fetch from './fetch.png'
import './PostForm.css'
class PostForm extends Component{
  constructor(props){
    super(props);

    this.state={
      name: "",
      email: "",
      password: "",
      occupation: "",
      states: ""

    }
  }
changeHandler = e => {
		this.setState({ [e.target.name]: e.target.value })
	}

	submitHandler = e => {
		e.preventDefault()
		console.log(this.state)
		axios
			.post('https://frontend-take-home.fetchrewards.com/form', this.state)
			.then(response => {
				console.log(response)
			})
			.catch(error => {
				console.log(error)
			})
	}
  render(){
    const { name, email, password, occupation, states } = this.state
    return(
      <div className='form-content-right'>
        <form onSubmit={this.submitHandler}>
          <div className='form-inputs'>
          <label className='form-label'>Name</label>
          <input
              className='form-input'
							type="text"
							name="name"
							value={name}
              placeholder='Enter your name'
							onChange={this.changeHandler}
						/>
          </div>
          <div className='form-inputs'>
          <label className='form-label'>Email</label>
          <input
              className='form-input'
							type="text"
							name="email"
							value={email}
              placeholder='Enter your email'
							onChange={this.changeHandler}
						/>
          </div>
          <div className='form-inputs'>
          <label className='form-label'>Password</label>
          <input
              className='form-input'
							type="text"
							name="password"
							value={password}
              placeholder='Enter your Password'
							onChange={this.changeHandler}
						/>
          </div>
          <div className='form-inputs'>
          <label className='form-label'>Occupation</label>
          <input
              className='form-input'
							type="text"
							name="occupation"
							value={occupation}
              placeholder='Enter your occupation'
							onChange={this.changeHandler}
						/>
          </div>
          <div className='form-inputs'>
          <label className='form-label'>State</label>
          <input
              className='form-input'
							type="text"
							name="states"
							value={states}
              placeholder='Enter the state you live in'
							onChange={this.changeHandler}
						/>
          </div>
          <button className='form-input-btn' type="submit">Submit</button>

        </form>


      </div>
    );
  }
  
}




export default PostForm;