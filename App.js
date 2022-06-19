import React, { Component } from 'react'
import './App.css'
import PostForm from './components/PostForm'
import PostList from './components/PostList'

class App extends Component{
  render(){
    return(      
        <div className="App">
				<PostForm />
				{/* <PostList /> */}
			</div>
    )
  }
}

export default App;
