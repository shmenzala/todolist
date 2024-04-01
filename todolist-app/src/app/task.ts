export interface Tasks {
    id: number;
    title: string;
    description: string;
    created_at: Date;
    status: string;
}

export interface TasksFields {
    title: string;
    description: string;
}