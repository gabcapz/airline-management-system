import { BrowserRouter, Routes, Route, NavLink } from 'react-router-dom'
import { useState, useEffect } from 'react'
import { LayoutDashboard, Users, Building2, Plane, Wrench, Menu, ChevronRight } from 'lucide-react'
import './index.css'

const API = 'http://localhost:8080/api'
const NAV = [
  { to: '/', label: 'Dashboard', icon: LayoutDashboard },
  { to: '/employees', label: 'Employees', icon: Users },
  { to: '/departments', label: 'Departments', icon: Building2 },
  { to: '/flights', label: 'Flights', icon: Plane },
  { to: '/aircraft', label: 'Aircraft', icon: Plane },
  { to: '/maintenance', label: 'Maintenance', icon: Wrench },
]

function Layout({ children }) {
  const [open, setOpen] = useState(true)
  return (
    <div style={{ display: 'flex', minHeight: '100vh', background: '#f4f6f9', fontFamily: 'Inter, system-ui, sans-serif', color: '#1a2332' }}>
      <aside style={{ width: open ? 232 : 64, background: '#0b2545', color: '#fff', transition: 'width .2s', overflow: 'hidden', display: 'flex', flexDirection: 'column' }}>
        <div style={{ padding: '20px 16px', display: 'flex', alignItems: 'center', gap: 10, borderBottom: '1px solid rgba(255,255,255,.08)' }}>
          <button onClick={() => setOpen(!open)} style={{ background: 'none', border: 'none', color: '#fff', cursor: 'pointer' }}><Menu size={20} /></button>
          {open && <span style={{ fontWeight: 700, fontSize: 18, letterSpacing: '.3px' }}>SkyManage</span>}
        </div>
        <nav style={{ padding: 8, flex: 1 }}>
          {NAV.map(({ to, label, icon: Icon }) => (
            <NavLink key={to} to={to} end={to === '/'} style={({ isActive }) => ({
              display: 'flex', alignItems: 'center', gap: 12, padding: '10px 12px', borderRadius: 8, color: isActive ? '#fff' : 'rgba(255,255,255,.7)',
              background: isActive ? 'rgba(255,255,255,.12)' : 'transparent', textDecoration: 'none', marginBottom: 2, fontWeight: isActive ? 600 : 400, fontSize: 14
            })}>
              <Icon size={18} /> {open && label}
            </NavLink>
          ))}
        </nav>
      </aside>
      <div style={{ flex: 1, display: 'flex', flexDirection: 'column' }}>
        <header style={{ background: '#fff', padding: '14px 28px', borderBottom: '1px solid #e6eaf0', display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
          <h1 style={{ margin: 0, fontSize: 18, fontWeight: 600 }}>Airline Workforce & Operations Portal</h1>
          <div style={{ display: 'flex', alignItems: 'center', gap: 8, color: '#6b7280', fontSize: 13 }}>
            <ChevronRight size={14} /> Admin
          </div>
        </header>
        <main style={{ flex: 1, padding: 28, overflow: 'auto' }}>{children}</main>
      </div>
    </div>
  )
}

const card = { background: '#fff', borderRadius: 10, padding: 20, boxShadow: '0 1px 2px rgba(16,24,40,.06)', border: '1px solid #e6eaf0' }

function Dashboard() {
  const [emp, setEmp] = useState([])
  const [dept, setDept] = useState([])
  const [flights, setFlights] = useState([])
  const [aircraft, setAircraft] = useState([])
  const [maint, setMaint] = useState([])
  useEffect(() => {
    fetch(`${API}/employees`).then(r => r.json()).then(setEmp).catch(() => {})
    fetch(`${API}/departments`).then(r => r.json()).then(setDept).catch(() => {})
    fetch(`${API}/flights`).then(r => r.json()).then(setFlights).catch(() => {})
    fetch(`${API}/aircraft`).then(r => r.json()).then(setAircraft).catch(() => {})
    fetch(`${API}/maintenance`).then(r => r.json()).then(setMaint).catch(() => {})
  }, [])
  const cards = [
    { label: 'Employees', value: emp.length },
    { label: 'Departments', value: dept.length },
    { label: 'Flights', value: flights.length },
    { label: 'Aircraft', value: aircraft.length },
    { label: 'Maintenance Records', value: maint.length },
  ]
  return (
    <div>
      <h2 style={{ margin: '0 0 16px', fontSize: 22 }}>Dashboard</h2>
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(180px,1fr))', gap: 16, marginBottom: 24 }}>
        {cards.map(c => (
          <div key={c.label} style={card}>
            <div style={{ color: '#6b7280', fontSize: 13 }}>{c.label}</div>
            <div style={{ fontSize: 30, fontWeight: 700, marginTop: 4 }}>{c.value}</div>
          </div>
        ))}
      </div>
      <div style={card}>
        <h3 style={{ margin: '0 0 12px', fontSize: 16 }}>Recent Employees</h3>
        {emp.length === 0 ? <div style={{ color: '#9ca3af' }}>No employees found.</div> :
          <table style={tableStyle()}>
            <thead><tr><th style={thStyle()}>ID</th><th style={thStyle()}>Name</th><th style={thStyle()}>Department</th><th style={thStyle()}>Type</th></tr></thead>
            <tbody>{emp.slice(0, 5).map(e => <tr key={e.employeeId}><td style={tdStyle()}>{e.employeeId}</td><td style={tdStyle()}>{e.firstName} {e.lastName}</td><td style={tdStyle()}>{e.departmentName || '—'}</td><td style={tdStyle()}>{e.employeeType}</td></tr>)}</tbody>
          </table>}
      </div>
    </div>
  )
}

function tableStyle() { return { width: '100%', borderCollapse: 'collapse', background: '#fff', borderRadius: 10, overflow: 'hidden', boxShadow: '0 1px 2px rgba(16,24,40,.06)', border: '1px solid #e6eaf0' } }
function thStyle() { return { textAlign: 'left', padding: '12px 16px', background: '#f9fafb', fontSize: 12, fontWeight: 600, color: '#6b7280', textTransform: 'uppercase', letterSpacing: '.5px', borderBottom: '1px solid #e6eaf0' } }
function tdStyle() { return { padding: '12px 16px', fontSize: 14, borderBottom: '1px solid #f0f2f5' } }

function Employees() {
  const [list, setList] = useState([])
  const [q, setQ] = useState('')
  useEffect(() => { fetch(`${API}/employees${q ? '?q=' + encodeURIComponent(q) : ''}`).then(r => r.json()).then(setList).catch(() => {}) }, [q])
  return (
    <div>
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: 16 }}>
        <h2 style={{ margin: 0, fontSize: 22 }}>Employees</h2>
        <input placeholder="Search employees..." value={q} onChange={e => setQ(e.target.value)} style={{ padding: '8px 12px', border: '1px solid #d1d5db', borderRadius: 8, width: 260, fontSize: 14 }} />
      </div>
      <table style={tableStyle()}>
        <thead><tr><th style={thStyle()}>ID</th><th style={thStyle()}>Name</th><th style={thStyle()}>Department</th><th style={thStyle()}>Type</th><th style={thStyle()}>Phone</th><th style={thStyle()}>Degree</th></tr></thead>
        <tbody>{list.map(e => <tr key={e.employeeId}><td style={tdStyle()}>{e.employeeId}</td><td style={tdStyle()}>{e.firstName} {e.lastName}</td><td style={tdStyle()}>{e.departmentName || '—'}</td><td style={tdStyle()}><span style={{ background: '#eef2ff', color: '#4338ca', padding: '2px 8px', borderRadius: 6, fontSize: 12 }}>{e.employeeType}</span></td><td style={tdStyle()}>{e.phoneNumber || '—'}</td><td style={tdStyle()}>{e.degree || '—'}</td></tr>)}</tbody>
      </table>
    </div>
  )
}

function Departments() {
  const [list, setList] = useState([])
  useEffect(() => { fetch(`${API}/departments`).then(r => r.json()).then(setList).catch(() => {}) }, [])
  return (
    <div>
      <h2 style={{ margin: '0 0 16px', fontSize: 22 }}>Departments</h2>
      {list.length === 0 ? <div style={{ ...card, color: '#9ca3af' }}>No departments found.</div> :
      <table style={tableStyle()}>
        <thead><tr><th style={thStyle()}>ID</th><th style={thStyle()}>Name</th><th style={thStyle()}>Location</th><th style={thStyle()}>Employees</th></tr></thead>
        <tbody>{list.map(d => <tr key={d.departmentId}><td style={tdStyle()}>{d.departmentId}</td><td style={tdStyle()}>{d.name}</td><td style={tdStyle()}>{d.location || '—'}</td><td style={tdStyle()}>{d.employeeCount}</td></tr>)}</tbody>
      </table>
      }
    </div>
  )
}

function Flights() {
  const [list, setList] = useState([])
  useEffect(() => { fetch(`${API}/flights`).then(r => r.json()).then(setList).catch(() => {}) }, [])
  return (
    <div>
      <h2 style={{ margin: '0 0 16px', fontSize: 22 }}>Flights</h2>
      {list.length === 0 ? <div style={{ ...card, color: '#9ca3af' }}>No flights found.</div> :
      <table style={tableStyle()}>
        <thead><tr><th style={thStyle()}>Flight No.</th><th style={thStyle()}>Distance</th><th style={thStyle()}>Departure</th><th style={thStyle()}>Arrival</th><th style={thStyle()}>Aircraft</th></tr></thead>
        <tbody>{list.map(f => <tr key={f.flightNumber}><td style={tdStyle()}>{f.flightNumber}</td><td style={tdStyle()}>{f.distance ?? '—'}</td><td style={tdStyle()}>{f.departureAirport?.airportCode || '—'}</td><td style={tdStyle()}>{f.arrivalAirport?.airportCode || '—'}</td><td style={tdStyle()}>{f.aircraft?.model || '—'}</td></tr>)}</tbody>
      </table>}
    </div>
  )
}
function Aircraft() {
  const [list, setList] = useState([])
  useEffect(() => { fetch(`${API}/aircraft`).then(r => r.json()).then(setList).catch(() => {}) }, [])
  return (
    <div>
      <h2 style={{ margin: '0 0 16px', fontSize: 22 }}>Aircraft</h2>
      {list.length === 0 ? <div style={{ ...card, color: '#9ca3af' }}>No aircraft found.</div> :
      <table style={tableStyle()}>
        <thead><tr><th style={thStyle()}>ID</th><th style={thStyle()}>Model</th><th style={thStyle()}>Capacity</th><th style={thStyle()}>Status</th></tr></thead>
        <tbody>{list.map(a => <tr key={a.aircraftId}><td style={tdStyle()}>{a.aircraftId}</td><td style={tdStyle()}>{a.model}</td><td style={tdStyle()}>{a.capacity ?? '—'}</td><td style={tdStyle()}><span style={{ background: a.status === 'ACTIVE' ? '#ecfdf5' : '#fef2f2', color: a.status === 'ACTIVE' ? '#047857' : '#b91c1c', padding: '2px 8px', borderRadius: 6, fontSize: 12 }}>{a.status}</span></td></tr>)}</tbody>
      </table>}
    </div>
  )
}

function Maintenance() {
  const [list, setList] = useState([])
  useEffect(() => { fetch(`${API}/maintenance`).then(r => r.json()).then(setList).catch(() => {}) }, [])
  return (
    <div>
      <h2 style={{ margin: '0 0 16px', fontSize: 22 }}>Maintenance</h2>
      {list.length === 0 ? <div style={{ ...card, color: '#9ca3af' }}>No maintenance records found.</div> :
      <table style={tableStyle()}>
        <thead><tr><th style={thStyle()}>ID</th><th style={thStyle()}>Type</th><th style={thStyle()}>Status</th><th style={thStyle()}>Start Date</th><th style={thStyle()}>End Date</th></tr></thead>
        <tbody>{list.map(m => <tr key={m.maintenanceId}><td style={tdStyle()}>{m.maintenanceId}</td><td style={tdStyle()}>{m.type || '—'}</td><td style={tdStyle()}><span style={{ background: m.status === 'COMPLETED' ? '#ecfdf5' : '#fef3c7', color: m.status === 'COMPLETED' ? '#047857' : '#92400e', padding: '2px 8px', borderRadius: 6, fontSize: 12 }}>{m.status}</span></td><td style={tdStyle()}>{m.startDate || '—'}</td><td style={tdStyle()}>{m.endDate || '—'}</td></tr>)}</tbody>
      </table>}
    </div>
  )
}
function SimpleList({ title, endpoint, cols }) {
  const [list, setList] = useState([])
  return (
    <div>
      <h2 style={{ margin: '0 0 16px', fontSize: 22 }}>{title}</h2>
      {list.length === 0 ? <div style={{ ...card, color: '#9ca3af' }}>No records found.</div> :
      <table style={tableStyle()}>
        <thead><tr>{cols.map(c => <th key={c} style={thStyle()}>{c}</th>)}</tr></thead>
        <tbody>{list.map((r, i) => <tr key={i}>{cols.map(c => {
          const key = c.toLowerCase().replace(/ /g, '')
          const v = r[key] ?? r[c] ?? ''
          return <td key={c} style={tdStyle()}>{String(v)}</td>
        })}</tr>)}</tbody>
      </table>}
    </div>
  )
}

export default function App() {
  return (
    <BrowserRouter>
      <Layout>
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/employees" element={<Employees />} />
          <Route path="/departments" element={<Departments />} />
          <Route path="/flights" element={<Flights />} />
          <Route path="/aircraft" element={<Aircraft />} />
          <Route path="/maintenance" element={<Maintenance />} />
        </Routes>
      </Layout>
    </BrowserRouter>
  )
}
