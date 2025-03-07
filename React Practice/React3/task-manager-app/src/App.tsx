import React, { useState, useEffect } from 'react';
import { Button, TextField, Box } from '@mui/material';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import TaskList from './components/TaskList';

// Define Task type with 'completed' field
type Task = {
  id: number;
  name: string;
  completed: boolean;
};

const App: React.FC = () => {
  const [taskName, setTaskName] = useState<string>(''); // Name of the task being added
  const [tasks, setTasks] = useState<Task[]>([]); // Array of tasks
  const [editingTaskId, setEditingTaskId] = useState<number | null>(null); // Track the task being edited

  // Load tasks from localStorage on initial load
  useEffect(() => {
    const savedTasks = localStorage.getItem('tasks');
    if (savedTasks) {
      setTasks(JSON.parse(savedTasks));
    }
  }, []);

  // Save tasks to localStorage whenever tasks change
  useEffect(() => {
    if (tasks.length > 0) {
      localStorage.setItem('tasks', JSON.stringify(tasks));
    }
  }, [tasks]);

  // Handle adding a new task
  const handleAddTask = (): void => {
    if (taskName.trim() === '') return;

    const newTask: Task = {
      id: tasks.length + 1,
      name: taskName,
      completed: false, // New tasks are not completed by default
    };

    setTasks((prevTasks) => [...prevTasks, newTask]);
    setTaskName('');
  };

  // Handle toggling the completion status of a task
  const handleToggleComplete = (id: number): void => {
    setTasks((prevTasks) =>
      prevTasks.map((task) =>
        task.id === id ? { ...task, completed: !task.completed } : task
      )
    );
  };

  // Handle removing a task
  const handleRemoveTask = (id: number): void => {
    setTasks((prevTasks) => prevTasks.filter((task) => task.id !== id));
  };

  // Start editing a task
  const handleEditTask = (id: number, name: string): void => {
    setEditingTaskId(id);
    setTaskName(name);
  };

  // Save edited task
  const handleSaveEdit = (): void => {
    if (editingTaskId !== null && taskName.trim() !== '') {
      setTasks((prevTasks) =>
        prevTasks.map((task) =>
          task.id === editingTaskId ? { ...task, name: taskName } : task
        )
      );
      setEditingTaskId(null);
      setTaskName('');
    }
  };

  return (
    <Router future={{ v7_startTransition: true }}>
      <Box sx={{ padding: '20px', maxWidth: 400, margin: '0 auto'}}>
        <div style={{
          padding: '30px',
          position: 'fixed',
          top:'42.5%',
          left:'42.5%',
          border: 'solid',
          borderColor: 'lightgrey',
          backgroundColor: '#E4DEE4'
        }}>
        <h1 style={{
          textAlign: 'center'

        }}>Task Manager</h1>

        {/* Task input field */}
        <TextField
          color="dark"
          label={editingTaskId ? 'Edit Task' : 'New Task'}
          variant="outlined"
          fullWidth
          value={taskName}
          onChange={(e) => setTaskName(e.target.value)}
          sx={{ marginBottom: 2 }}
          data-testid="task-input"
        />

        {/* Add or Save task button */}
        <Button
          variant="contained"
          color="secondary"
          onClick={editingTaskId ? handleSaveEdit : handleAddTask}
          fullWidth
          data-testid="add-task-button"
        >
          {editingTaskId ? 'Save Edit' : 'Add Task'}
        </Button>

        {/* Navigation Links */}
        <Box sx={{ marginTop: 2, display: 'flex',  justifyContent: 'center', gap: '10px' }}>
          <Link to="/" style={{ textDecoration: 'none' }}>
            <Button variant="outlined" data-testid="all-tasks-link" color='secondary'>
              All Tasks
            </Button>
          </Link>
          <Link to="/active" style={{ textDecoration: 'none' }}>
            <Button variant="outlined" data-testid="active-tasks-link" color='secondary'>
              Active Tasks
            </Button>
          </Link>
          <Link to="/completed" style={{ textDecoration: 'none' }}>
            <Button variant="outlined" data-testid="completed-tasks-link" color='secondary'>
              Completed Tasks
            </Button>
          </Link>
        </Box>

        {/* Routes */}
        <Routes>
          <Route
            path="/"
            element={
              <TaskList
                tasks={tasks}
                handleToggleComplete={handleToggleComplete}
                handleRemoveTask={handleRemoveTask}
                handleEditTask={handleEditTask}
              />
            }
          />
          <Route
            path="/active"
            element={
              <TaskList
                tasks={tasks.filter((task) => !task.completed)}
                handleToggleComplete={handleToggleComplete}
                handleRemoveTask={handleRemoveTask}
                handleEditTask={handleEditTask}
              />
            }
          />
          <Route
            path="/completed"
            element={
              <TaskList
                tasks={tasks.filter((task) => task.completed)}
                handleToggleComplete={handleToggleComplete}
                handleRemoveTask={handleRemoveTask}
                handleEditTask={handleEditTask}
              />
            }
          />
        </Routes>
        </div>
      </Box>
    </Router>
  );
};

export default App
