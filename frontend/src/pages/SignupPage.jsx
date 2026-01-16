import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { signup } from '../services/apiClient';

export function SignupPage() {
  const navigate = useNavigate();
  const [form, setForm] = useState({
    fullName: '',
    mobile: '',
    email: '',
    country: '',
    city: ''
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);
    try {
      const data = await signup(form);
      localStorage.setItem('authToken', data.token);
      localStorage.setItem('userId', data.userId);
      localStorage.setItem('fullName', data.fullName || form.fullName);
      navigate('/role');
    } catch (err) {
      setError('Could not complete signup. Please check your details and try again.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <section className="space-y-4">
      <h1 className="text-2xl font-semibold">Create your account</h1>
      <p className="text-sm text-slate-700">
        We ask a few basic details to personalize your experience and match you to the right
        opportunities. It takes less than a minute.
      </p>
      <form onSubmit={handleSubmit} className="space-y-3 max-w-md">
        <Field label="Full Name" name="fullName" value={form.fullName} onChange={handleChange} />
        <Field label="Mobile Number" name="mobile" value={form.mobile} onChange={handleChange} />
        <Field label="Email" name="email" type="email" value={form.email} onChange={handleChange} />
        <Field label="Country" name="country" value={form.country} onChange={handleChange} />
        <Field label="City" name="city" value={form.city} onChange={handleChange} />
        {error && <p className="text-sm text-red-600">{error}</p>}
        <button
          type="submit"
          disabled={loading}
          className="inline-flex items-center rounded-md bg-blue-600 px-4 py-2 text-white text-sm disabled:opacity-70"
        >
          {loading ? 'Creating account...' : 'Continue'}
        </button>
      </form>
    </section>
  );
}

function Field({ label, name, value, onChange, type = 'text' }) {
  return (
    <label className="block text-sm">
      <span className="mb-1 inline-block text-slate-700">{label}</span>
      <input
        type={type}
        name={name}
        value={value}
        onChange={onChange}
        className="mt-1 block w-full rounded-md border px-3 py-2 text-sm"
        required
      />
    </label>
  );
}

